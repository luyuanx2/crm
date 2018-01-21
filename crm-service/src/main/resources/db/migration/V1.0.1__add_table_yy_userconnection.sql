DROP TABLE IF EXISTS `yy_UserConnection`;
create table yy_UserConnection (userId varchar(128) not null,
	providerId varchar(128) not null,
	providerUserId varchar(128),
	rank int not null,
	displayName varchar(255),
	profileUrl varchar(512),
	imageUrl varchar(512),
	accessToken varchar(512) not null,
	secret varchar(512),
	refreshToken varchar(512),
	expireTime bigint,
	primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on yy_UserConnection(userId, providerId, rank);