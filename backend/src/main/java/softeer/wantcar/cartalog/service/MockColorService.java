package softeer.wantcar.cartalog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import softeer.wantcar.cartalog.entity.color.ExteriorColor;
import softeer.wantcar.cartalog.entity.color.InteriorColor;
import softeer.wantcar.cartalog.entity.color.TrimExteriorColor;
import softeer.wantcar.cartalog.entity.color.TrimInteriorColor;

import java.util.List;

@Slf4j
@Service
public class MockColorService implements ColorService {

    private volatile TrimExteriorColor trimExteriorColor;
    private volatile TrimInteriorColor trimInteriorColor;

    public TrimExteriorColor getMockData() {
        if (trimExteriorColor == null) {
            synchronized (MockColorService.class) {
                if (trimExteriorColor == null) {
                    log.debug("mock 객체가 생성되었습니다. :{}", TrimExteriorColor.class);
                    trimExteriorColor = TrimExteriorColor.builder()
                            .id(1L)
                            .color(ExteriorColor.builder()
                                    .id("A2B")
                                    .name("어비스 블랙 펄")
                                    .code("141423")
                                    .build())
                            .price(0)
                            .chosen(38)
                            .build();
                }
            }
        }
        return trimExteriorColor;
    }

    public TrimInteriorColor getMockData2() {
        if (trimInteriorColor == null) {
            synchronized (MockColorService.class) {
                if (trimInteriorColor == null) {
                    log.debug("mock 객체가 생성되었습니다. :{}", TrimInteriorColor.class);
                    trimInteriorColor = TrimInteriorColor.builder()
                            .id(1L)
                            .color(InteriorColor.builder()
                                    .id("A22")
                                    .name("퀼팅천연(블랙)")
                                    .imageUrl("example-url/colors/interior/I49.png")
                                    .build())
                            .price(0)
                            .chosen(38)
                            .build();
                }
            }
        }
        return trimInteriorColor;
    }

    @Override
    public List<TrimExteriorColor> findTrimExteriorColorListByTrimId(Long trimId) {
        if (trimId == 1) {
            log.debug("mock 객체가 호출되었습니다. :{}", TrimExteriorColor.class);
            return List.of(getMockData());
        } else {
            throw new RuntimeException("존재하지 않은 트림 식별자 입니다.");
        }
    }

    @Override
    public List<TrimInteriorColor> findTrimInteriorColorListByTrimId(Long trimId, Long exteriorColorId) {
        if (trimId == 1 && exteriorColorId == 1) {
            log.debug("mock 객체가 호출되었습니다. :{}", TrimInteriorColor.class);
            return List.of(getMockData2());
        } else {
            throw new RuntimeException("존재하지 않은 트림 및 외장 색상 식별자 조합 입니다.");
        }
    }
}