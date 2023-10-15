package br.com.temal.jdbcClientTest;

import java.lang.*;
import java.util.Date;

public record DirectDebitEntry(int id, Double ammout , String expireDate ,  String status ) {

}