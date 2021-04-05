Insert into FiscalCheck(SystemCode,EntityCode,srcID,CorrelationID,Date,OrganizationInn, FilialCode)
values( 'ADINSURE', 'Payment', '--srcID--', '--CID--', '2019-07-12 00:00:00.000', '--inn--', '--filialCode--');

Insert into Customer(SystemCode,EntityCode,srcID, FullName,INN, Phone)
values( 'ADINSURE', 'Payment', '--srcID--','Фамилия Имя Отчество', '--inn--', '71000000000' );

Insert into Payment(SystemCode,EntityCode,srcID,Amount,PaymentTypeCode)
values( 'ADINSURE', 'Payment', '--srcID--', '1522756', 'INCOME');

Insert into Position(SystemCode,EntityCode,srcID,"Number",Name,Price,Quantity,TaxCode)
values( 'ADINSURE', 'Payment', '--srcID--', '123456', 'Страховая премия № 100000000', '1234', '1234', 'VAT20');

insert into ProcessStatus(SystemCode, EntityCode, srcID, fiscGateCorrelationID)
select top 1 f.SystemCode, f.EntityCode, f.srcID, CorrelationID
from FiscalCheck as f left join ProcessStatus as p on p.SystemCode = f.SystemCode and p.EntityCode = f.EntityCode and p.srcID = f.srcID
where p.EntityCode is null and f.srcID = '--srcID--'

update alertPeriod set finishTime = '--finishTime--' where FilialCode = '--filialCode--'
update alertPeriod set startTime = '--startTime--' where FilialCode = '--filialCode--'
update fiscalCheck set filialCode = '--filialCode--' where srcId = '--srcID--'
update processStatus set fiscGateCheckStatusCode = null where srcId = '--srcID--'
select * from processStatus where srcID = '--srcID--'