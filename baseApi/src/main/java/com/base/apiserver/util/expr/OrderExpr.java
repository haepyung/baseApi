package com.base.apiserver.util.expr;

import com.querydsl.core.types.OrderSpecifier;

/**
 * QueryDSL ORDER BY 조건 추가 유틸
 * @author PARK
 *
 */
public class OrderExpr
{
	private OrderSpecifier<?> expr = null;
	
	public OrderExpr and(OrderSpecifier<?> o)
	{
		if (o == null) return this;
		if (expr == null)
			expr = o;
		else
			expr = expr.nullsLast();
		
		return this;
	}

	public OrderSpecifier<?> build()
	{
		return expr;
	}
}
