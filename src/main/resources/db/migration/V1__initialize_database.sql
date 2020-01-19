
CREATE TABLE `user` (
                         `UserID` bigint(20) NOT NULL AUTO_INCREMENT,
                         `UserDisplayName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                         `UserEmail` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                         `UserPassword` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                         `UserRoleGroup` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                         `UserCreated` datetime DEFAULT NULL,
                        PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `hotel` (
                         `HotelID` bigint(20) NOT NULL AUTO_INCREMENT,
                         `HotelName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                         `HotelAddress` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `HotelDescription` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `HotelLat` DECIMAL(10, 8) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `HotelLng` DECIMAL(11, 8) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `HotelImage` longblob DEFAULT NULL,
                         `HotelOverallRating` DECIMAL(4, 2) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `HotelCreated` datetime DEFAULT NULL,
                         `HotelUserID` bigint(20) NOT NULL COMMENT 'User created a hotel',
                        KEY `hotel_user_FK` (`HotelUserID`),
                        CONSTRAINT `hotel_user_FK` FOREIGN KEY (`HotelUserID`) REFERENCES `user` (`UserID`),
                        PRIMARY KEY (`HotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `review` (
                         `ReviewID` bigint(20) NOT NULL AUTO_INCREMENT,
                         `ReviewDescription` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `ReviewRate` int COLLATE utf8mb4_general_ci NOT NULL,
                         `ReviewCalculatedRate` DECIMAL(4, 2) COLLATE utf8mb4_general_ci DEFAULT NULL,
                         `ReviewCreated` datetime DEFAULT NULL,
                         `ReviewUserID` bigint(20) NOT NULL COMMENT 'User created a review',
                         `ReviewHotelID` bigint(20) NOT NULL COMMENT 'Hotel for review',
                        KEY `review_user_FK` (`ReviewUserID`),
                        KEY `review_hotel_FK` (`ReviewHotelID`),
                        CONSTRAINT `review_hotel_FK` FOREIGN KEY (`ReviewHotelID`) REFERENCES `hotel` (`HotelID`),
                        CONSTRAINT `review_user_FK` FOREIGN KEY (`ReviewUserID`) REFERENCES `user` (`UserID`),
                        PRIMARY KEY (`ReviewID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `review_confirmation` (
                         `ReviewConfirmationID` bigint(20) NOT NULL AUTO_INCREMENT,
                         `ReviewConfirmationType` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'LIKE OR DISLIKE',
                         `ReviewConfirmationUserID` bigint(20) NOT NULL COMMENT 'User created a review',
                         `ReviewConfirmationReviewID` bigint(20) NOT NULL ,
                        KEY `confirmation_user_FK` (`ReviewConfirmationUserID`),
                        KEY `confirmation_review_FK` (`ReviewConfirmationReviewID`),
                        CONSTRAINT `confirmation_user_FK` FOREIGN KEY (`ReviewConfirmationUserID`) REFERENCES `user` (`UserID`),
                        CONSTRAINT `confirmation_review_FK` FOREIGN KEY (`ReviewConfirmationReviewID`) REFERENCES `review` (`ReviewID`),
                        PRIMARY KEY (`ReviewConfirmationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `review_confirmation` ADD UNIQUE `review_confirmation_unique`(`ReviewConfirmationUserID`, `ReviewConfirmationReviewID`);



