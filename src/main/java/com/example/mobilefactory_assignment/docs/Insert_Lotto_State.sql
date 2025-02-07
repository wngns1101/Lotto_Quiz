INSERT INTO lotto_state (
    count_rank_1,
    count_rank_2,
    count_rank_3,
    count_rank_4,
    created_at,
    first_prize_lotto_number,
    first_prize_phone_number
) VALUES (
             1,                   -- count_rank_1 값 (예시)
             5,                   -- count_rank_2 값 (예시)
             44,                   -- count_rank_3 값 (예시)
             950,                   -- count_rank_4 값 (예시)
             NOW(),                 -- created_at 값 (현재 시간, 예시)
             '1.12.20.34.38.40',         -- first_prize_lotto_number 값 (예시: 당첨 번호)
             '010-1111-1111'        -- first_prize_phone_number 값 (예시: 당첨 전화번호)
         );