USE projekt2_gruppe_4_db;

    -- showing the reserved wishes --
SELECT
    wish_id,
    title,
    reserved_by_user_id
FROM wish;

-- selecting from all three tables --
USE projekt2_gruppe_4_db;

SELECT sw.share_id, w.wishlist_id,
       sw.shared_with_user_id,
       w.title,
       u.name AS owner
FROM SharedWishlist sw
         JOIN Wishlist w ON sw.wishlist_id = w.wishlist_id
         JOIN Users u ON w.user_id = u.user_id
WHERE sw.shared_with_user_id = ?
