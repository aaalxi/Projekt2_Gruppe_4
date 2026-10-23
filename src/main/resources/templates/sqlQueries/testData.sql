-- TEST ENVIRONMENT --

USE onskeskyen;
-- Ava: password is 1234, Mads: GodmorgenEK, Rasmus: 4321 --

INSERT INTO users (name, email, password_hash)
VALUES
    ('Ava', 'aval0001@stud.ek.dk', '$2a$12$Pv.ByUh4NSIPLYT2OoByVe/S9XDAmlpW4TgMuqUF3XgYPUIWgWj2q'),
    ('Mads', 'ava0001@stud.ek.dk', '$2a$12$ZhM5L6vKLXiIUAFBSwnIf.0qRGjCjekxe/HhgZwvmhZ85VCeVCcdK'),
    ('Rasmus', 'av0001@stud.ek.dk', '$2a$12$pcNK7eBBXbJ0c16gQZ0lwObvNKG1oXSVrd95DpG/kh7cXxsh5Tj5e');

INSERT INTO wishlist (user_id, title)
VALUES
    (1, 'Julegaver'),
    (2, 'Barnedåb'),
    (3, 'Ny lejlighed 26');

-- Ava wants socks for Christmas --
INSERT INTO wish(wishlist_id, title, product_url, price, img_url) VALUES
    (1, 'Sokker','https://mayafreya.dk/products/winnie-mocha-brown-cozy-socks?variant=55411777110392&utm_source=google&utm_medium=cpc&utm_campaign=19607289276&utm_country=DK&utm_content=202292706434&utm_term=&tw_source=google&tw_adid=820877613530&tw_campaign=19607289276&gad_source=1&gad_campaignid=19607289276&gbraid=0AAAAACnEfTHWiViKn_0Xa3-P0iyhNDDy3&gclid=Cj0KCQjw6ObWBhDDARIsAFdfcUikq9-vKZQfhroWjdIAHUZaqX0fpwvSgnKKQcowsrhdz5lbefRoNRoaApGAEALw_wcB',
     99.00,'https://www.mayafreya.se/cdn/shop/files/Winnie_Mocha_Brown_Cosy_Socks_2_pack_4.jpg?crop=center&height=1100&v=1780642578&width=880');

-- Rasmus wishes to get three pots for his apartment, for price 500 and a desk as first priority--
INSERT INTO wish(wishlist_id, title, price, wish_amount, priority) VALUES (3, 'Gryder', 500, 3, 2);
INSERT INTO wish(wishlist_id, title, product_url, wish_amount, priority)
VALUES (3, 'Skrivebord', 'https://www.ikea.com/dk/da/p/utmaning-gamerhaeve-saenkebord-elektrisk-sort-s09571665/',
                 1, 1);

-- Sharedwishlist: Mads is sharing his wishlist 'Barnedåb' with Rasmus --
INSERT INTO sharedwishlist (wishlist_id, shared_with_user_id) VALUES (2, 3);

-- Mads wants cutlery for his son, that Rasmus as a User has reserved--
INSERT INTO wish(wishlist_id, title, product_url, price, wish_amount, img_url, priority, reserved_by_user_id)
VALUES (2, 'Dåbsbestik','https://c-centrum.dk/vare/daabsbestik-m-drager-noa/?gad_source=1&gad_campaignid=23272412096&gbraid=0AAAAADi3RJ1JirP0vtvJpY-JkZ60osp6O&gclid=Cj0KCQjw6ObWBhDDARIsAFdfcUjD_NFpeDG65cqduNSNkzKur6_uifdnM0WOcrgL3ssatBlgxloYuB0aAvzjEALw_wcB',
        395.00, 2,'https://c-centrum.dk/wp-content/uploads/2025/11/93002527016-Bestik-med-drage-bamse.webp',
        1, 3);