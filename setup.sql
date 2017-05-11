CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `tweets` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`text` varchar(255) NOT NULL,
	`user` BIGINT(20) UNSIGNED NOT NULL,
	`reply_to` BIGINT(20) UNSIGNED DEFAULT NULL,
	`retweet_of` BIGINT(20) UNSIGNED DEFAULT NULL,
	`retweet_count` INT UNSIGNED DEFAULT 0,
	PRIMARY KEY (`id`),
	INDEX `user` (`user`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
ALTER TABLE `tweets` ADD CONSTRAINT `tweet_user_fk` FOREIGN KEY (`user`) REFERENCES `users`(`id`);
ALTER TABLE `tweets` ADD CONSTRAINT `tweet_reply_tweet_fk` FOREIGN KEY (`reply_to`) REFERENCES `tweets`(`id`);
ALTER TABLE `tweets` ADD CONSTRAINT `tweet_retweet_tweet_fk` FOREIGN KEY (`retweet_of`) REFERENCES `tweets`(`id`);

CREATE TABLE `likes` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) UNSIGNED NOT NULL,
	`tweet_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `user_id` (`user_id`),
	INDEX `tweet_id` (`tweet_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
ALTER TABLE `likes` ADD CONSTRAINT `like_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);
ALTER TABLE `likes` ADD CONSTRAINT `like_tweet_fk` FOREIGN KEY (`tweet_id`) REFERENCES `tweets`(`id`);

CREATE TABLE `mentions` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) UNSIGNED NOT NULL,
	`tweet_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `user_id` (`user_id`),
	INDEX `tweet_id` (`tweet_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
ALTER TABLE `mentions` ADD CONSTRAINT `mention_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);
ALTER TABLE `mentions` ADD CONSTRAINT `mention_tweet_fk` FOREIGN KEY (`tweet_id`) REFERENCES `tweets`(`id`);

CREATE TABLE `timelines` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `user_id` (`user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
ALTER TABLE `timelines` ADD CONSTRAINT `timeline_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

CREATE TABLE `timeline_tweets` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`timeline_id` BIGINT(20) UNSIGNED NOT NULL,
	`tweet_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `timeline_id` (`timeline_id`),
	INDEX `tweet_id` (`tweet_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
ALTER TABLE `timeline_tweets` ADD CONSTRAINT `timeline_tweet_timeline_fk` FOREIGN KEY (`timeline_id`) REFERENCES `timelines`(`id`);
ALTER TABLE `timeline_tweets` ADD CONSTRAINT `timeline_tweet_tweet_fk` FOREIGN KEY (`tweet_id`) REFERENCES `tweets`(`id`);