package com.cosmos.fax.models.response.jwt;

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
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;
}