insert into ProcessStatus(SystemCode, EntityCode, srcID, fiscGateCorrelationID)
select top 1 f.SystemCode, f.EntityCode, f.srcID, CorrelationID
from FiscalCheck as f left join ProcessStatus as p on p.SystemCode = f.SystemCode and p.EntityCode = f.EntityCode and p.srcID = f.srcID
where p.EntityCode is null and f.srcID = '--srcID--'