package softeer.wantcar.cartalog.estimate.repository;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import softeer.wantcar.cartalog.estimate.repository.dto.EstimateCountDto;
import softeer.wantcar.cartalog.estimate.repository.dto.EstimateOptionListDto;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("견적 Repository 테스트")
@JdbcTest
@Transactional
@Sql({"classpath:schema.sql"})
class EstimateQueryRepositoryTest {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    EstimateQueryRepository estimateQueryRepository;
    SoftAssertions softAssertions;

    @BeforeEach
    void setUp() {
        estimateQueryRepository = new EstimateQueryRepositoryImpl(jdbcTemplate);
        softAssertions = new SoftAssertions();
    }

    @Nested
    @DisplayName("findEstimateOptionIdsByEstimateId 테스트")
    class findEstimateOptionIdsByEstimateIdTest {
        @Test
        @DisplayName("존재하는 견적 식별자를 전달할 경우, 견적의 트림 식별자, 선택 옵션 식별자 목록, 패키지 식별자 목록을 반환한다")
        void returnEstimateInfo() {
            //given
            //when
            EstimateOptionListDto estimateInfo = estimateQueryRepository.findEstimateOptionIdsByEstimateId(1L);

            //then
            softAssertions.assertThat(estimateInfo).isNotNull();
            softAssertions.assertThat(estimateInfo.getOptionIds()).isNotNull();
            softAssertions.assertThat(estimateInfo.getPackageIds()).isNotNull();
            List<Character> prefixes = estimateInfo.getAllOptionIds().stream()
                    .map(id -> id.charAt(0))
                    .distinct()
                    .collect(Collectors.toList());
            softAssertions.assertThat(prefixes.size())
                    .isLessThanOrEqualTo(2);
            softAssertions.assertAll();
        }

        @Test
        @DisplayName("존재하지 않는 견적 식별자를 전달할 경우 null을 반환한다")
        void returnNull() {
            //given
            //when
            EstimateOptionListDto estimateInfo = estimateQueryRepository.findEstimateOptionIdsByEstimateId(-1L);

            //then
            assertThat(estimateInfo).isNull();
        }
    }

    @Nested
    @DisplayName("findEstimateCounts 테스트")
    class findEstimateCountsTest {
        //TODO: 등록된 유사 견적이 존재하지 않아 테스트 불가
        @Test
        @DisplayName("존재하는 견적 식별자들을 전달할 경우 견적의 개수를 반환한다")
        void returnEstimateCounts() {
            //given
            //when
            List<EstimateCountDto> estimateCounts = estimateQueryRepository.findEstimateCounts(List.of(1L, 2L, 3L));

            //then
            softAssertions.assertThat(estimateCounts.size()).isEqualTo(3);
            for (int estimateId = 1; estimateId <= estimateCounts.size(); estimateId++) {
                softAssertions.assertThat(estimateCounts.get(estimateId - 1).getEstimateId())
                        .isEqualTo(estimateId);
            }
//            softAssertions.assertAll();
        }

        @Test
        @DisplayName("존재하지 않는 견적 식별자들만을 전달할 경우 빈 List를 반환한다")
        void returnEmptyList() {
            //given
            //when
            List<EstimateCountDto> estimateCounts = estimateQueryRepository.findEstimateCounts(List.of(1L, 2L, 3L));

            //then
            assertThat(estimateCounts.isEmpty()).isTrue();
        }
    }
}
