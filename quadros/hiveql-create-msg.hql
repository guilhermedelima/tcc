create table social_messages(
  timestamp STRING,
  year STRING,
  month STRING,
  day STRING,
  message STRING,
  author STRING,
  politician STRING,
  classification STRING,
  type STRING
)
ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'
STORED AS TEXTFILE;
