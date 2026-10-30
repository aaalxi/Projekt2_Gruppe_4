-- TEST ENVIRONMENT --

USE projekt2_gruppe_4_db;

-- Alle's password er "1234" --
INSERT INTO users (name, email, password_hash)
VALUES
    ('Bjørn', 'F26A@stud.ek.dk','$2a$10$Wa7YhIz8a0InzHidWtvQXuXKqOw.tEvH0oOSrfetNEEIA8VUySNxW'),
    ('Ava', 'aval0001@stud.ek.dk', '$2a$10$Wa7YhIz8a0InzHidWtvQXuXKqOw.tEvH0oOSrfetNEEIA8VUySNxW'),
    ('Mads', 'majo1009@stud.ek.dk', '$2a$10$Wa7YhIz8a0InzHidWtvQXuXKqOw.tEvH0oOSrfetNEEIA8VUySNxW'),
    ('Rasmus', 'ragu1000 @stud.ek.dk', '$2a$10$Wa7YhIz8a0InzHidWtvQXuXKqOw.tEvH0oOSrfetNEEIA8VUySNxW');

INSERT INTO wishlist (user_id, title)
VALUES
    (1, 'Julegaver 2026'),
    (1, 'Fødselsdagsfest!'),
    (2, 'Fødselsdag 🎉🥳🎉'),
    (3,'Barnedåb'),
    (4,'Fødselsdag for Lumi 🐶');


-- Bjørn's wishes for "Julegaver 2026" --
INSERT INTO wish(wishlist_id, title, product_url, price, img_url, priority)
VALUES
    (1, 'Laserpind', 'https://www.lomax.dk/elektronik/lyd-og-billede/projektorer-og-laerreder/presentere-og-pegepinde/logitech-professional-presenter-r400-3020760/?pla=1&ss_gc=CjwKCAjwgJHXBhBGEiwAHohJq6-5lwsgVA2hG6W1P5AbVZpks_kCwlvMHO1SSVSpPrKWB9Q-qGop-hoC_WoQAvD_BwE&utm_source=google&utm_medium=cpc&utm_campaign=02%3A%20Smart%20Shopping%20%7C%201%20%7C%20Catch%20all&gclsrc=aw.ds&gad_source=1&gad_campaignid=17419601032&gclid=CjwKCAjwgJHXBhBGEiwAHohJq6-5lwsgVA2hG6W1P5AbVZpks_kCwlvMHO1SSVSpPrKWB9Q-qGop-hoC_WoQAvD_BwE',
    423.75, 'https://cdn.lomax.dk/images/t_item_large/f_auto/v1559288621/produkter/3020760/logitech-professional-presenter-r400.jpg', 5),

    (1, 'Kaffe Kop', 'https://www.spreadshirt.dk/shop/design/programmering+programmerer+og+programkode+ensfarvet+krus-D5c88ba9522250935956639dc?sellable=GBYjRxeg5Juo5LdgO0mm-949-32&affiliateId=1262301&promo=53Mdk&gad_campaignid=17182145020&gad_source=1&gclid=CjwKCAjwgJHXBhBGEiwAHohJqxWl4WigYXc-hxcdHfsgS7bAHutkZSuxykutR018HPRoiqxnawfBBBoCdOgQAvD_BwE',
    139.00, 'https://image.spreadshirtmedia.net/image-server/v1/products/T949A2PA2007PT25X1Y0D158443387W5697H6837/views/1,appearanceId=2.jpg?width=1200&height=1600&backgroundColor=F2F2F2&modelId=11687&crop=detail', 3),

    (1, 'Ny arbejds komputer', 'https://www.lenovo.com/dk/da/p/workstations/thinkstationp/thinkstation-p7-workstation/30f3cto1wwdk3?orgRef=https%253A%252F%252Fwww.google.com%252F&cid=dk:sem:wax0xj&gclsrc=aw.ds&gad_source=1&gad_campaignid=22578755004&gclid=CjwKCAjwgJHXBhBGEiwAHohJq1p6AhIxX2hAIatW5LNJ0Mysd93m4uvNXI9ISg1VsIwI4NAeCe8woRoCkMQQAvD_BwE',
    90198.16,'https://p1-ofp.static.pub//fes/cms/2024/09/12/d2v4zn2fg92pb6myygd5f9ejnamhsx505536.png?width=584&height=584', 5),

    (1, 'Vinter handsker', 'https://kuskestuen.dk/produkt/vinterhandsker-standard/', 429.00, 'https://kuskestuen.dk/wp-content/uploads/2024/08/Gloves.Winter_crop.jpg', 2);

-- Bjørn's wishes for "Fødselsdagsfest!" --
INSERT INTO wish(wishlist_id, title, product_url, price, img_url, priority)
VALUES
    (2,'En lille ferie', 'https://www.spies.dk/jamaica/lucea/grand-palladium-jamaica-resort-spa?QueryDepID=12678&QueryResID=126378&ItemId=126380&HotelId=126380&QueryDepDate=20261126&QueryAges=42,42&QueryRoomAges=42,42&QueryUnits=1&QueryRetDate=20261203&QueryChkInDate=20261126&QueryChkOutDate=20261203&QueryDur=-1&QueryType=IndependentPackage&qf=flowDynamic&SessionId=u2tu2y1e4lsd34i2ribgekhe',
    35090, 'https://img.spies.dk/image/upload/c_fill,g_xy_center,x_1200,y_977,w_2560,f_avif,q_auto:eco/dam-upload/Hotel/Jamaica/MBJ/xLc5nmo/JM_MBJ_Grand_Palladium_Jama_xLc5nmo_00086.jpg', 3),

    (2, 'Lidt legetøj til børnebørn her til sommeren', 'https://www.kids-world.dk/dantoy-spandsaet-8-dele-blaa-p-431436.html?source=google_shopping&sssource=google_shopping&gad_source=1&gad_campaignid=9831679378&gclid=CjwKCAjwgJHXBhBGEiwAHohJqwMrgnefd_KUO7v-V7crIZZNuzjaxue4Jy0BFrUvKysI9z58fTslJRoCKA4QAvD_BwE',
    119.95, 'https://cdn.kids-world.dk/images/products/WT816/417px/WT816.jpg?v=1772545536', 2),

    (2, 'One billiob dollars!', 'https://en.wikipedia.org/wiki/Get-rich-quick_scheme', 10000000, 'https://martech.org/wp-content/uploads/2012/04/1-billion-dollars.png', 5),

    (2, 'Bentley Continental GT V8 S Convertible aut.', 'https://carclub.dk/biler/bentley/continental-gt/continental-gt-v8-s-convertible-aut/?_gl=1*p8zt3p*_up*MQ..*_ga*MjA4MDU5ODk5MS4xNzkzMzU0ODQw*_ga_BKZHBMQ7T0*czE3OTMzNTQ4MzkkbzEkZzAkdDE3OTMzNTQ4MzkkajYwJGwwJGgxNjI0Mzg1ODE1',
    1795000, 'https://billeder.bilinfo.net/bilinfo/8680aece-5d2f-4767-9ecd-d7795ae7fabc.jpeg?class=S1600X1600',4);


-- Ava's wishes for "Fødselsdag 🎉🥳🎉" --
INSERT INTO wish(wishlist_id, title, product_url, price, img_url, priority)
VALUES
    (3, 'Sokker','https://mayafreya.dk/products/winnie-mocha-brown-cozy-socks?variant=55411777110392&utm_source=google&utm_medium=cpc&utm_campaign=19607289276&utm_country=DK&utm_content=202292706434&utm_term=&tw_source=google&tw_adid=820877613530&tw_campaign=19607289276&gad_source=1&gad_campaignid=19607289276&gbraid=0AAAAACnEfTHWiViKn_0Xa3-P0iyhNDDy3&gclid=Cj0KCQjw6ObWBhDDARIsAFdfcUikq9-vKZQfhroWjdIAHUZaqX0fpwvSgnKKQcowsrhdz5lbefRoNRoaApGAEALw_wcB',
     99.00,'https://www.mayafreya.se/cdn/shop/files/Winnie_Mocha_Brown_Cosy_Socks_2_pack_4.jpg?crop=center&height=1100&v=1780642578&width=880', 1),

    (3, 'Vinter jakke', 'https://www.fashionqueen.dk/dame-vinterjakke-med-aftagelig-fake-fur-pels-model-paris-i-gron.html?srsltid=AYCX9vJd4i3oT7AAkrUIUn6wOJOt0VXp3qPQNRd_CmBSF9SJfn50RJR6',
        699.00, 'https://www.fashionqueen.dk/media/catalog/product/cache/b0477abfd1c5e646cd30ff774ae97d00/d/a/dame_vinterjakke_paris_green_bl_001_2_1.jpg', 4),

    (3, 'Lenovo ThinkPad', 'https://www.lomax.dk/elektronik/computere-og-tablets/baerbare-computere/lenovo-thinkpad-e16-g2-ryzen5-16-ips-16gb-ram-70279850/?pla=1&ss_gc=CjwKCAjwgJHXBhBGEiwAHohJq_wN4eIQy1cywNzFE60T3Xcmc9PBKzHJlIWo30U_YS-cF1jhDVxG3BoCy9AQAvD_BwE&utm_source=google&utm_medium=cpc&utm_campaign=02%3A%20Smart%20Shopping%20%7C%201%20%7C%20Catch%20all&gclsrc=aw.ds&gad_source=1&gad_campaignid=17419601032&gclid=CjwKCAjwgJHXBhBGEiwAHohJq_wN4eIQy1cywNzFE60T3Xcmc9PBKzHJlIWo30U_YS-cF1jhDVxG3BoCy9AQAvD_BwE',
     8123.75, 'https://cdn.lomax.dk/images/t_item_large/f_auto/v1784800449/produkter/70279850_1/lenovo-thinkpad-e16-g2-ryzen5-16-ips-16gb-ram-1.jpg', '5');

INSERT INTO wish(wishlist_id, title, img_url)
VALUES
    (3, 'Penge', 'https://dingaeld.dk/wp-content/uploads/2023/03/pexels-skitterphoto-9660-min.jpg');


-- Mads's wishes for "Barnedåb"--
INSERT INTO wish(wishlist_id, title, product_url, price, img_url, priority)
VALUES
        (4, 'Dåbsbestik','https://c-centrum.dk/vare/daabsbestik-m-drager-noa/?gad_source=1&gad_campaignid=23272412096&gbraid=0AAAAADi3RJ1JirP0vtvJpY-JkZ60osp6O&gclid=Cj0KCQjw6ObWBhDDARIsAFdfcUjD_NFpeDG65cqduNSNkzKur6_uifdnM0WOcrgL3ssatBlgxloYuB0aAvzjEALw_wcB',
        395.00,'https://c-centrum.dk/wp-content/uploads/2025/11/93002527016-Bestik-med-drage-bamse.webp',1),

        (4, 'El Bil', 'https://www.lomax.dk/flere-produkter/fritid-og-leg/el-drevne-koeretoejer/elbil-mercedes-benz-amg-g63-boernebil-12v-sort-70106390/?pla=1&ss_gc=CjwKCAjwgJHXBhBGEiwAHohJq_6E6mU5MEzR4AeQYXN41T0dwGKbs2MzpXwvel6lvcW24lavNWhOCxoCiawQAvD_BwE&utm_source=google&utm_medium=cpc&utm_campaign=02%3A%20Smart%20Shopping%20%7C%201%20%7C%20Catch%20all&gclsrc=aw.ds&gad_source=1&gad_campaignid=17419601032&gclid=CjwKCAjwgJHXBhBGEiwAHohJq_6E6mU5MEzR4AeQYXN41T0dwGKbs2MzpXwvel6lvcW24lavNWhOCxoCiawQAvD_BwE',
         1748.75,'https://cdn.lomax.dk/images/t_item_large/f_auto/v1693821625/produkter/70106390_1/elbil-mercedes-benz-amg-g63-bornebil-sort-1.jpg', 3);

INSERT INTO wish(wishlist_id, title, img_url, priority)
VALUES
        (4, 'Børnepasnings klippekort hos bedsteforældre', 'https://sensitiv.dk/wp-content/uploads/2017/08/Bedstefor%C3%A6ldre-Gode-ra%CC%8Ad-til-typiske-udfordringer-web84455403.jpeg', 5);

-- Rasmus's wishes for "Fødselsdag for Lumi 🐶"--
INSERT INTO wish(wishlist_id, title, product_url, price, img_url, priority)
VALUES
    (5, 'aktivitetslegetøj', 'https://satana.dk/products/hunde-snack-aktivitetslegetoej-til-godbidder',
     89, 'https://satana.dk/cdn/shop/files/hundelegetoej06_8d35168b-36c7-4114-a719-3c46541aef61.jpg?v=1745450612&width=1000', 4),

    (5, 'Pindsvinbolde', 'https://www.zoopet.dk/da/pindsvinbold-vinyl-med-piv-oe7?utm_source=google&utm_medium=cpc&utm_campaign=08%3A%20Pmax%20%7C%20Zombies%20%7C%20Feed%20Only&gad_source=1&gad_campaignid=22540599733&gclid=CjwKCAjwgJHXBhBGEiwAHohJq7L8ed5bp0qK-joEuyEEOFbNmqw4ZCz9mPs56zs5tKYdKO01T_EYvhoCjm8QAvD_BwE',
     29,'https://www.megapetshop.dk/cdn/shop/files/TX3412_650c0c0c-c634-4e31-b61c-d56cf0187ead.jpg?v=1764840643&width=900', 3),

    (5, 'Hundeøl', 'https://vuffeli.dk/collections/hundol', 29.95, 'https://cdn.shopify.com/s/files/1/2721/1236/files/7_49726e24-d061-4887-98a6-f0ec5e2b3292_1200x.png?v=1765200645', 5);

INSERT INTO wish(wishlist_id, title, img_url, priority)
VALUES
    (5,'hunde godter!', 'https://www.hundeglad.dk/wp-content/uploads/2023/06/lykkepose-1.jpg', 5);

-- Sharedwishlist --
INSERT INTO sharedwishlist (wishlist_id, shared_with_user_id) VALUES (3, 1);
INSERT INTO sharedwishlist (wishlist_id, shared_with_user_id) VALUES (4, 1);
INSERT INTO sharedwishlist (wishlist_id, shared_with_user_id) VALUES (5, 1);