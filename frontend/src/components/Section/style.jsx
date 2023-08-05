import styled, { StyleSheetManager } from 'styled-components';

export const Section = styled.div`
  min-width: 100%;
  margin-top: 60px;
`;

export const Background = styled.div`
  ${({ type, url, theme }) => {
    switch (type) {
      case 'TrimSelect':
        return `
          background: ${theme.color.trimGrad};
        `;
      case 'ModelType':
        return `
          background: ${theme.color.modelGrad};
        `;
      case 'InteriorColor':
        return `
          background: url(${url});
          background-size: cover;
        `;
      case 'AddOption':
        return `
          display: flex;
          flex-direction: row;
        `;
      default:
        return `
          background: ${theme.color.blueBG};
        `;
    }
  }}
  height: 360px;
  box-shadow: 0px 0px 8px 0px rgba(131, 133, 136, 0.2);
`;

export const ColorDiv = styled.div`
  min-width: 50%;
  background: ${({ theme }) => theme.color.blueBG};
`;

export const ImageDiv = styled.div`
  min-width: 50%;
  ${({ url }) => `
    background: url(${url});
    background-size: cover;
  `};
`;

export const Contents = styled.div`
  display: flex;
  flex-direction: column;
  width: 1280px;
  margin: 0 auto;
  padding: 0 128px;
  box-sizing: border-box;
`;
