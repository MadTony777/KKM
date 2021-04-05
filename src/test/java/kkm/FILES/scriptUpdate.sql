update dbo.Customer set inn = '1' where srcID = '--srcID--'
update ProcessStatus set fiscGateCheckStatusCode = null where srcID = '--srcID--'
select * from ProcessStatus where srcID = '--srcID--'