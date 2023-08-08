import * as S from './style';
import RoundButton from '../../../components/RoundButton';
import Button from '../../../components/Button';
import PickTitle from '../../../components/PickTitle';

const TYPE = 'buttonD';
const STATE = 'active';
const MAIN_TITLE = '다음';
const PICK_MAIN_TITLE = '모델타입을 선택해주세요.';

function Pick() {
  const pickTitleProps = { mainTitle: PICK_MAIN_TITLE };
  const buttonProps = {
    type: TYPE,
    state: STATE,
    mainTitle: MAIN_TITLE,
  };

  const roundButtonProps = {
    type: 'price',
    state: 'active',
    title: '견적 요약',
  };

  return (
    <S.Pick>
      <PickTitle {...pickTitleProps} />
      <RoundButton {...roundButtonProps} />
      <Button {...buttonProps} />
    </S.Pick>
  );
}

export default Pick;
