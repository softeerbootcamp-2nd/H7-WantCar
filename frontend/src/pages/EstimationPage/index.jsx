import { useEffect, useState } from 'react';
import { useData, TotalPrice } from '../../utils/Context';
import PriceStaticBar from '../../components/PriceStaticBar';
import Preview from './Preview';
import * as S from './style';
import Info from './Info';
import Detail from './Detail';
import HMGArea from './HMGArea';

const MOCK_DATA = [
  {
    title: '모델타입',
    expand: true,
    data: [
      {
        id: 1,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 2,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 3,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
    ],
  },
  {
    title: '색상',
    expand: true,
    data: [
      {
        id: 4,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 5,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 6,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
    ],
  },
  {
    title: '추가옵션',
    data: [
      {
        id: 7,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 8,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
      {
        id: 9,
        title: '파워트레인',
        name: '디젤 2.2',
        image: 'https://via.placeholder.com/770x550',
        price: 280000,
      },
    ],
  },
];

const MOCK_HMGDATA = {
  trim: '르블랑',
  price: {
    min: 43460000,
    max: 47650000,
    current: 45800000,
  },
  value: 2500,
  similar: [
    {
      id: 1,
      value: 3422,
    },
    {
      id: 2,
      value: 3200,
    },
    {
      id: 3,
      value: 3000,
    },
    {
      id: 4,
      value: 2800,
    },
  ],
};

function Estimation() {
  const { setTrimState, page, trim, price, estimation } = useData();
  const SelectModel = trim.fetchData.find((model) => model.id === trim.id);

  // !FIX API 데이터 받아오도록 수정해야함 유사견적 API
  useEffect(() => {
    async function fetchData() {
      if (!estimation.isFetch && page === 6) {
        setTrimState((prevState) => ({
          ...prevState,
          estimation: {
            ...prevState.estimation,
            isFetch: true,
          },
        }));
      }
    }

    fetchData();
  }, [page]);

  return estimation.isFetch ? (
    <S.Estimation>
      <Preview />
      <S.Estimation>
        <Info />
        <S.PageContents>
          <Detail data={MOCK_DATA} />
          <HMGArea data={MOCK_HMGDATA} />
        </S.PageContents>
      </S.Estimation>
      <PriceStaticBar
        min={SelectModel?.minPrice}
        max={SelectModel?.maxPrice}
        price={TotalPrice(price)}
      />
    </S.Estimation>
  ) : (
    <>Loading...</>
  );
}

export default Estimation;
