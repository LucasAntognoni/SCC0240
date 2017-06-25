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