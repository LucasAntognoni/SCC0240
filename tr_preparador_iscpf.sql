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