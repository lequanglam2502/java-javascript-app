package com.colin.app.util;

public class AuditConstant {
	private AuditConstant() {
	}

	public static final class AuditAction {
		public static final String ADD = "add";
		public static final String UPDATE = "update";
		public static final String DELETE = "delete";
		public static final String FIND = "find";
	}
}
