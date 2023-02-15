use retromall;

create table tb_member
(
    member_id  bigint unsigned not null auto_increment primary key,
    oauth_type varchar(10)     not null,
    oauth_id   varchar(255)    not null,
    email      varchar(100),
    nickname   varchar(100)
) engine = InnoDB
  charset = utf8;

create table tb_product
(
    product_id    bigint unsigned not null auto_increment primary key,
    author_id     bigint unsigned not null,
    content       text,
    amount        int             not null,
    category_name varchar(50)     not null,
    created_at    datetime        not null,
    modified_at   datetime        not null
) engine = InnoDB
  charset = utf8;

create table tb_hashtag
(
    hashtag_name varchar(50) not null primary key
) engine = InnoDB
  charset = utf8;

create table tb_category
(
    category_name     varchar(50) not null primary key,
    parent            varchar(50),
    is_classification boolean
) engine = InnoDB
  charset = utf8;

create table tb_product_image
(
    id         bigint unsigned not null primary key auto_increment,
    image_url  varchar(255)    not null,
    product_id bigint unsigned not null
) engine = InnoDB
  charset = utf8;

create table tb_product_hashtag
(
    id           bigint unsigned not null primary key auto_increment,
    product_id   bigint unsigned not null,
    hashtag_name bigint unsigned not null
) engine = InnoDB
  charset = utf8;

alter table tb_member
    add constraint tb_member_oauth_unique unique (oauth_id);
alter table tb_member
    add constraint tb_member_email_unique unique (email);

alter table tb_product
    add constraint tb_product_author_foreign_key foreign key (author_id) references tb_member (member_id);
alter table tb_product
    add constraint tb_product_category_foreign_key foreign key (category_name) references tb_category (category_name);

alter table tb_product_image
    add constraint tb_product_image_product_foreign foreign key (product_id) references tb_product (product_id);

alter table tb_product_hashtag
    add constraint tb_product_hashtag_product_foreign foreign key (product_id) references tb_product (product_id);
alter table tb_product_hashtag
    add constraint tb_product_hashtag_hashtag_foreign foreign key (hashtag_name) references tb_hashtag (hashtag_name);

alter table tb_category
    add constraint tb_category_parent_foreign foreign key (parent) references tb_category (category_name);