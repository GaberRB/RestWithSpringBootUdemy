CREATE TABLE IF NOT EXISTS  `books`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(100) NOT NULL,
    `description` varchar(250) NOT NULL,
    `price` decimal(12,2) NOT NULL,
    `stars` decimal(10,2) NOT NULL,
    PRIMARY KEY (`id`)
)