DROP TABLE IF EXISTS `yy_persistent_logins`;
CREATE TABLE `yy_persistent_logins` (
  `username`  VARCHAR(64) NOT NULL,
  `series`    VARCHAR(64) PRIMARY KEY,
  `token`     VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP   NOT NULL
);