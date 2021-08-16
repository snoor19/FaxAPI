package com.cosmos.fax.models.request.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 
 * @author snoor
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;
}