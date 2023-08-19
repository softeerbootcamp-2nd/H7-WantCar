import { useData, TotalPrice } from '../../../utils/Context';
import { FOOTER } from '../constants';
import * as S from './style';
import Button from '../../../components/Button';
import ImageViewButton from '../../../components/ImageViewButton';

function Footer() {
  const { price } = useData();

  const shareButtonProps = {
    type: FOOTER.TYPE,
    state: FOOTER.INACTIVE,
    mainTitle: FOOTER.SHARE_TITLE,
    event: () => {},
  };

  const pdfButtonProps = {
    type: FOOTER.TYPE,
    state: FOOTER.INACTIVE,
    mainTitle: FOOTER.PDF_TITLE,
    event: () => {},
  };

  const counsultButtonProps = {
    type: FOOTER.TYPE,
    state: FOOTER.ACTIVE,
    mainTitle: FOOTER.COUNSULT_TITLE,
    event: () => {},
  };

  const imageViewButtonProps = {
    onClick: () => {},
    text: FOOTER.CHECK_IMAGE,
  };

  return (
    <S.Footer>
      <S.ImageViewButtonWrapper>
        <ImageViewButton {...imageViewButtonProps} />
      </S.ImageViewButtonWrapper>

      <S.TotalPrice>
        <S.TotalPriceText>{FOOTER.FINAL_PRICE}</S.TotalPriceText>
        <S.TotalPriceNumber>
          {TotalPrice(price).toLocaleString('ko-KR')}
          {FOOTER.WON}
        </S.TotalPriceNumber>
      </S.TotalPrice>
      <S.ButtonWrapper>
        <Button {...shareButtonProps} />
        <Button {...pdfButtonProps} />
        <Button {...counsultButtonProps} />
      </S.ButtonWrapper>
    </S.Footer>
  );
}

export default Footer;
