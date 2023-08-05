import * as S from './style';

/**
 * Button을 보여주는 컴포넌트
 * @param nextPage {event} 다음 페이지로 넘어가는 함수
 * @param type {string} buttonA || buttonB || buttonC || buttonD
 * @param state {string} active || hover || click || inactive
 * @param subTitle {string} 서브 타이틀 작성
 * @param mainTitle {string} 메인 타이틀 작성
 * @returns
 */
function Button({ nextPage, type, state, subTitle, mainTitle }) {
  return (
    <S.Button onClick={nextPage} type={type} state={state}>
      <S.SubTitle>{subTitle}</S.SubTitle>
      <S.MainTitle>{mainTitle}</S.MainTitle>
    </S.Button>
  );
}

export default Button;