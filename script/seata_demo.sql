DROP TABLE IF EXISTS undo_log;
CREATE TABLE undo_log
(
    branch_id     bigint(20)   NOT NULL COMMENT 'branch transaction id',
    xid           varchar(128) NOT NULL COMMENT 'global transaction id',
    context       varchar(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    rollback_info longblob     NOT NULL COMMENT 'rollback info',
    log_status    int(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    log_created   datetime(6)  NOT NULL COMMENT 'create datetime',
    log_modified  datetime(6)  NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT = 'AT transaction mode undo table';

DROP TABLE IF EXISTS account;
CREATE TABLE account
(
    user_id        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    usable_balance int(11) unsigned NOT NULL DEFAULT '0' COMMENT '可用余额，单位：分',
    PRIMARY KEY (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '预存款';

INSERT INTO account
VALUES (1, 100);

DROP TABLE IF EXISTS inventory;
CREATE TABLE inventory
(
    product_id int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
    stock_num  int(11) unsigned NOT NULL DEFAULT '0' COMMENT '当前库存',
    PRIMARY KEY (product_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '库存';

INSERT INTO inventory
VALUES (1, 100);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id          int(11) unsigned NOT NULL DEFAULT '0' COMMENT '订单号',
    user_id     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
    product_id  int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
    product_num int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品数',
    pay_num     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '支付金额，单位：分',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '订单';
