import { useState, useRef } from 'react';
import * as S from './style';
import Chart from '../../../../components/Chart';
import HMGCard from '../../../../components/HMGCard';
import useIntersectionObserver from '../../../../hooks/useIntersectionObserver';
import SimilarPopup from '../SimilarPopup';

function SimilarCard({ data }) {
  const [show, setShow] = useState(false);
  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);
  const targetRef = useRef();
  const isInViewport = useIntersectionObserver(targetRef);
  const max = data?.similar?.reduce((acc, cur) => Math.max(acc, cur.value), 0);

  return (
    <HMGCard
      title={
        <>
          <span>내 견적과 비슷한 실제 출고 견적</span>들을
          <br />
          확인하고 비교해보세요.
        </>
      }
      description="유사 출고 견적이란, 내 견적과 해시태그 유사도가 높은 다른 사람들의 실제 출고 견적이에요."
    >
      <S.ChartWrapper
        className={isInViewport ? null : 'stop'}
        ref={targetRef}
        $n={(data?.similar?.length ?? 0) + 1}
      >
        <Chart active value={data?.value} max={max} />
        {data?.similar?.map((item) => (
          <Chart key={item.id} value={item.value} max={max} />
        ))}
      </S.ChartWrapper>
      <S.Button onClick={handleShow}>유사 출고 견적 확인하기</S.Button>
      <SimilarPopup show={show} close={handleClose} />
    </HMGCard>
  );
}

export default SimilarCard;