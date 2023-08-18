package softeer.wantcar.cartalog.estimate.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import softeer.wantcar.cartalog.estimate.dao.PendingHashTagSimilaritySaveDao;

@Repository
@RequiredArgsConstructor
@Transactional
public class SimilarityCommandRepositoryImpl implements SimilarityCommandRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void savePendingHashTagSimilarity(PendingHashTagSimilaritySaveDao pendingHashTagSimilaritySaveDao) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("pending_hash_tag_left_key", pendingHashTagSimilaritySaveDao.getPendingHashTagLeftKey())
                .addValue("hash_tag_key", pendingHashTagSimilaritySaveDao.getHashTagKey())
                .addValue("trim_id", pendingHashTagSimilaritySaveDao.getTrimId());

        String addPendingHashTagSimilaritiesQuery =
                "INSERT INTO pending_hash_tag_similarities VALUES ( :pending_hash_tag_left_key, :hash_tag_key, :trim_id )";

        jdbcTemplate.update(addPendingHashTagSimilaritiesQuery, parameters);
    }
}
