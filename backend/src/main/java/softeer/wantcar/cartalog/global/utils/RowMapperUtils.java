package softeer.wantcar.cartalog.global.utils;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RowMapperUtils {
    public interface RowMapperStrategy {
        boolean isMappingTarget(Class<?> type, String name);

        Object mapping(String name, ResultSet rs) throws SQLException;
    }

    private static final class IntegerStrategy implements RowMapperStrategy {
        @Override
        public boolean isMappingTarget(Class<?> type, String name) {
            return type == Integer.class || type == int.class;
        }

        @Override
        public Object mapping(String name, ResultSet rs) throws SQLException {
            return rs.getInt(name);
        }
    }

    private static final class LongStrategy implements RowMapperStrategy {
        @Override
        public boolean isMappingTarget(Class<?> type, String name) {
            return type == Long.class || type == long.class;
        }

        @Override
        public Object mapping(String name, ResultSet rs) throws SQLException {
            return rs.getLong(name);
        }
    }

    private static final class StringStrategy implements RowMapperStrategy {
        @Override
        public boolean isMappingTarget(Class<?> type, String name) {
            return type == String.class;
        }

        @Override
        public Object mapping(String name, ResultSet rs) throws SQLException {
            return rs.getString(name);
        }
    }

    private static final class FloatStrategy implements RowMapperStrategy {
        @Override
        public boolean isMappingTarget(Class<?> type, String name) {
            return type == Float.class;
        }

        @Override
        public Object mapping(String name, ResultSet rs) throws SQLException {
            return rs.getFloat(name);
        }
    }

    private static final class DoubleStrategy implements RowMapperStrategy {
        @Override
        public boolean isMappingTarget(Class<?> type, String name) {
            return type == Double.class;
        }

        @Override
        public Object mapping(String name, ResultSet rs) throws SQLException {
            return rs.getDouble(name);
        }
    }

    private static List<RowMapperStrategy> getDefaultRowMapperStrategies() {
        return List.of(new IntegerStrategy(), new LongStrategy(), new StringStrategy(), new FloatStrategy(), new DoubleStrategy());
    }

    public static <T> RowMapper<T> mapping(final Class<T> clazz) {
        return mapping(clazz, List.of());
    }

    public static <T> RowMapper<T> mapping(final Class<T> clazz, final RowMapperStrategy rowMapperStrategy) {
        return mapping(clazz, List.of(rowMapperStrategy));
    }

    public static <T> RowMapper<T> mapping(final Class<T> clazz, final List<RowMapperStrategy> rowMapperStrategies) {
        var array = Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getType)
                .toArray(Class[]::new);

        return (rs, rowNum) -> {
            List<Object> args = new ArrayList<>();
            for (Field field : clazz.getDeclaredFields()) {
                Class<?> type = field.getType();
                String name = convertSnakeCase(field.getName());

                List<RowMapperStrategy> strategies = new ArrayList<>();
                if (rowMapperStrategies != null) {
                    strategies.addAll(rowMapperStrategies);
                }
                strategies.addAll(getDefaultRowMapperStrategies());

                for (RowMapperStrategy rowMapperStrategy : strategies) {
                    if (rowMapperStrategy.isMappingTarget(type, name)) {
                        args.add(rowMapperStrategy.mapping(name, rs));
                        break;
                    }
                }
            }

            try {
                Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(array);
                return declaredConstructor.newInstance(args.toArray());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new IllegalArgumentException("자동 rowMapper 변환 불가능");
            }
        };
    }

    private static String convertSnakeCase(String fieldName) {
        return fieldName.replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase();
    }
}
