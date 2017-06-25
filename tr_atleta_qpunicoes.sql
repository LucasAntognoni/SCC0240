create or replace trigger tr_atleta_qpunicoes
after delete or insert or update on testedoping
for each row
begin

    if ( :old.atleta_id is not null and :old.ispositivo = 'S' ) then
        update atleta
        set qpunicoes = qpunicoes - 1
        where atleta_id = :new.atleta_id;
    end if;
    
    if ( :new.atleta_id is not null and :new.ispositivo = 'S' ) then
        update atleta
        set qpunicoes = qpunicoes + 1
        where atleta_id = :new.atleta_id;
    end if;
    
end;