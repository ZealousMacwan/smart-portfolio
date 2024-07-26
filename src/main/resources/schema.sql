CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    angel_user_id VARCHAR(255) UNIQUE NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    email_id VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE total_holdings (
    id SERIAL PRIMARY KEY,
    user_id_fk INTEGER NOT NULL,
    total_holding_value NUMERIC,
    total_profit_and_loss NUMERIC,
    total_pnl_percentage NUMERIC,
    total_inv_value NUMERIC,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id_fk) REFERENCES users (id)
);

CREATE TABLE holdings (
    id SERIAL PRIMARY KEY,
    user_id_fk INTEGER NOT NULL,
    t1_quantity INTEGER,
    authorised_quantity INTEGER,
    product VARCHAR(255),
    quantity INTEGER,
    ltp NUMERIC,
    haircut NUMERIC,
    profit_and_loss NUMERIC,
    collateral_type VARCHAR(255),
    average_price NUMERIC,
    trading_symbol VARCHAR(255),
    pnl_percentage NUMERIC,
    exchange VARCHAR(255),
    close NUMERIC,
    isin VARCHAR(255),
    realised_quantity INTEGER,
    symbol_token VARCHAR(255),
    collateral_quantity INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id_fk) REFERENCES users (id)
);

-- DROP table holdings, total_holdings, users;
-- TRUNCATE table holdings, total_holdings;
