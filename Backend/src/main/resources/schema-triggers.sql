-- USE `e_store` |
DROP PROCEDURE IF EXISTS `e_store`.`placeOrder`|;

CREATE
PROCEDURE `e_store`.`placeOrder` (IN req_id BIGINT)
BEGIN
INSERT INTO order_request(book_id, no_of_copies)
VALUES (
req_id, (
    SELECT threshold + 5
    FROM book
    WHERE book.book_id = req_id
));
END |;


-- FIRST
-- USE `e_store`|
DROP TRIGGER IF EXISTS `e_store`.`BOOK_BEFORE_INSERT`|;
CREATE
TRIGGER `e_store`.`BOOK_BEFORE_INSERT`
BEFORE INSERT ON `e_store`.`book`
FOR EACH ROW
BEGIN
IF NEW.no_of_copies < NEW.threshold THEN
    set @message_text = concat("Enter Copies More Than Threshold");
    signal sqlstate '45000' set message_text = @message_text;
    -- unhandled user-defined exception
END IF;
END |;

-- SECOND
-- USE `e_store`|
DROP TRIGGER IF EXISTS `e_store`.`AFTER_UPDATE_COPIES`|;
CREATE
TRIGGER `e_store`.`AFTER_UPDATE_COPIES`
AFTER UPDATE ON `e_store`.`book`
FOR EACH ROW
BEGIN
IF NEW.no_of_copies < NEW.threshold AND OLD.no_of_copies >= NEW.threshold then
    CALL placeOrder(NEW.book_id);
END If;
END |;

-- THIRD
-- USE `e_store`|
DROP TRIGGER IF EXISTS `e_store`.`BEFORE_UPDATE_COPIES`|;
CREATE
TRIGGER `e_store`.`BEFORE_UPDATE_COPIES`
BEFORE UPDATE ON `e_store`.`book`
FOR EACH ROW
BEGIN
IF (NEW.no_of_copies < 0) THEN
    SET @message_text = concat("Can't Update No Of Copies with Negative");
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
END IF;
END |;

-- Fourth
-- USE `e_store`|
DROP TRIGGER IF EXISTS `e_store`.`ORDERS_BEFORE_DELETE`|;
CREATE
TRIGGER `e_store`.`ORDERS_BEFORE_DELETE`
BEFORE DELETE ON `e_store`.`order_request`
FOR EACH ROW
BEGIN
UPDATE book As b
SET b.no_of_copies = b.no_of_copies + OLD.no_of_copies
WHERE OLD.book_id = b.book_id;
END |;