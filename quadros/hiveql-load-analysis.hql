INSERT OVERWRITE TABLE analysis_result
select politician, classification, type, year, month, count(*)
from social_messages
group by politician, classification, type, year, month;
