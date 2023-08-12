import styled from 'styled-components';

export const Pick = styled.div`
  display: flex;
  flex-direction: column;
`;

export const Color = styled.div`
  display: flex;
  margin-top: 12px;
  gap: 16px;
  overflow: hidden;
`;

export const Footer = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-bottom: 16px;
`;

export const FooterEnd = styled.div`
  position: fixed;
  z-index: 2;
  bottom: 0;
  margin-bottom: 16px;
`;
