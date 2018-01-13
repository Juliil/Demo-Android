package com.itm.mobile.dados.ormlite.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

interface Crud
{
    public int create(Object item) throws SQLException;
    public int update(Object item);
    public int delete(Object item);
    public Object findById(UUID id);
    public List<?> findAll();
}
