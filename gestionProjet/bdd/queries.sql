select * from users;
select * from category;
select * from sold_article;


select soldState, sa.noArticle, sa.articleName, sa.description, sa.bidStartPrice, sa.bidStartedDate, sa.bidEndDate, sa.soldPrice, sa.noCategory, sa.noUser, sa.noRetirement, u.noUser, u.surname, u.firstname, u.lastname, u.phone, u.email, u.credit, u.street, u.zipCode, u.city, ret.noRetirement as noRet , ret.street as streetRet, ret.zipCode as zipRet, ret.city as cityRet
from sold_article sa
inner join users u on sa.noUser = u.noUser
inner join retirement ret on sa.noRetirement = ret.noRetirement
where bidStartedDate <= current_timestamp and bidEndDate >= current_timestamp
ORDER BY sa.bidEndDate ASC;
