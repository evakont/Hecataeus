CREATE OR REPLACE VIEW DIAR_SAMPLES_V AS
SELECT
        S.sam_res_year,
        S.sam_rsc_code,
        S.sam_code,
        S.sam_last_name,
        S.sam_first_name,
        S.sam_fathername,
        S.sam_mothername,
        S.sam_birth_yr,
        S.sam_idcard,
        S.sam_afm,
        S.sam_address,
        S.sam_zipcode,
        S.sam_tel,
        S.sam_legalpers,
        S.sam_nmax,
        S.sam_nmin,
        S.sam_sm_code,
        S.sam_created_by,
        S.sam_changed_by,
        S.sam_date_created,
        S.sam_date_changed,
        S.sam_geocode,
        S.sam_lay_code,
        S.sm_cha_code,
        S.sam_member,
        S.sam_qued,
        S.sam_aa_qued,
        S.sam_section,
        S.sam_legal,
        S.sam_personal,
        S.sam_relation,
        S.sam_dept,
        S.sam_flg,
        S.sam_ypa,
        S.sam_ypacode,
        S.sam_block,
        S.sam_nomos,
        S.sam_dimos,
        S.sam_tsgm,
        s.sam_fintype, --���������
        S.sam_feme,
        S.sam_surfcode,--���������������� (--)
        S.sam_sydel,
        S.sam_street_number,
        s.sam_met,--������ʹ (1), ����������ʹ (2), ����� (3)
        s.sam_land, --���������������� ��, ������������ ��� ��� diar_questdets(cat_id=258)
        s.sam_totsum, --�������������� �� (���������������� - ������ ����������)(cat_ids=258 - 539)
        D.SAM_NUMBER_NEW,
        D.SAM_CURRENT_STATE,
        D.SAM_AGR_OCCUPATION,
        D.SAM_NEW_GEOCODE,
        D.SAM_TRAINING,
        D.SAM_TRAINING_LEVEL,
        D.SAM_CONSUMPTION,
        D.SAM_SALE,
        D.SAM_OTHER_POSITION,
        D.SAM_OTHER_GEOCODE,
        D.SAM_OTHER_AREA,
        D.SAM_OTHER_STAVLOI,
        D.SAM_CURRENT_AREA,
        D.SAM_CURRENT_STAVLOI,
        D.SAM_FACE,
        D.SAM_DURATION,
        D.SAM_DATE,
        D.SAM_COOPERATION,
        D.SAM_NOTES,
        D.SAM_NEW_LAST_NAME,
        D.SAM_NEW_FIRST_NAME,
        D.SAM_NEW_FATHERNAME,
        D.SAM_NEW_BIRTH_YR,
        D.SAM_NEW_IDCARD,
        D.SAM_NEW_LEGALPERS,
        D.SAM_NEW_AFM,
        D.SAM_NEW_STREET,
        D.SAM_NEW_STREET_NUMBER,
        D.SAM_NEW_ZIPCODE,
        D.SAM_NEW_TEL,
        D.SAM_BIO_ZWA_FLG
  From spr_samples S, spr_diar_samples D
  Where S.sam_rsc_code = D.sam_rsc_code
    and S.sam_res_year = D.sam_res_year
    and S.sam_code     = D.SAM_CODE
