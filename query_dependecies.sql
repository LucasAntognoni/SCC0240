select baseTable.* from all_constraints baseTable , all_constraints referentedTable
where baseTable.R_CONSTRAINT_NAME = referentedTable.CONSTRAINT_NAME
      and baseTable.constraint_type = 'R'
      and referentedTable.table_name = 'ATLETA';  -- parametro: tabela alvo