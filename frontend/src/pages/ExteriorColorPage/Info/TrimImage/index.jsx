import { useState } from 'react';
import { useData } from '../../../../utils/Context';
import * as S from './style';

// !FIX : API완성되면 상수 없애기
const trimSelect = 'le-blanc';

function TrimImage() {
  const { exteriorColor } = useData();
  const [image, setImage] = useState(1);
  const [prevX, setPrevX] = useState(0);

  const startSwipe = (event) => {
    if (exteriorColor.rotate) {
      setPrevX(event.clientX);
      if (prevX < event.clientX) {
        if (image <= 1) setImage(60);
        else setImage((prevImage) => prevImage - 1);
      }
      if (prevX > event.clientX) {
        if (image >= 60) setImage(1);
        else setImage((prevImage) => prevImage + 1);
      }
    }
  };

  return (
    <S.TrimImage>
      <S.RotateImage
        src={`${exteriorColor.pickCarImageUrl}/${trimSelect}/exterior/${
          exteriorColor.exteriorColorId
        }/${image.toString().padStart(3, '0')}.png`}
        onMouseMove={startSwipe}
      />
    </S.TrimImage>
  );
}

export default TrimImage;