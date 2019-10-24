package com.base.apiserver.util.expr;

import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * QueryDSL WHERE 조건 추가 유틸
 * @author PARK
 *
 */
public class Expr
{
	private BooleanExpression expr = null;
	
	public Expr and(BooleanExpression p)
	{
		if (p == null) return this;
		if (expr == null)
			expr = p;
		else
			expr = expr.and(p);
		
		return this;
	}
	
	public Expr or(BooleanExpression p)
	{
		if (p == null) return this;
		
		if (expr == null)
			expr = p;
		else
			expr = expr.or(p);
		
		return this;
	}

	public BooleanExpression build()
	{
		return expr;
	}
}
