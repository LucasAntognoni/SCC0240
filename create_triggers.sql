create or replace trigger tr_preparador_iscpf
before insert or update on preparador
for each row
    declare
        nacao_nome VARCHAR2(30);
    begin

        select n.nome into nacao_nome
        from nacao n
        where n.nacao_id = :new.nacao_id;

        if ( upper(nacao_nome) = 'BRASIL' ) then
            :new.iscpf := 'S';
        else
            :new.iscpf := 'N';
        end if;

    end;

create or replace trigger tr_atleta_iscpf
before insert or update on atleta
for each row
    declare
        nacao_nome VARCHAR2(30);
    begin

        select n.nome into nacao_nome
        from nacao n
        where n.nacao_id = :new.nacao_id;

        if ( upper(nacao_nome) = 'BRASIL' ) then
            :new.iscpf := 'S';
        else
            :new.iscpf := 'N';
        end if;

    end;

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

CREATE OR REPLACE FUNCTION sf_isAtletaImpedido (atleta_id in number)
    RETURN char is impedido char(1);
    diff number;
    BEGIN
        begin
            select min(sysdate - datahora) into diff
            from testedoping td
            where td.atleta_id = atleta_id
                  and ispositivo = 'S';

            impedido := 'N';
            if (diff < 90) then
                impedido := 'S';
            end if;
            exception when NO_DATA_FOUND then
            impedido := 'N';
        end;

        return impedido;
    END;
