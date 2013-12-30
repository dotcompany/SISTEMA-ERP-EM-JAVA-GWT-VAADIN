package dc.control.enums;

public enum FilhoEnum {

	FILHO_MENU {
		@Override
		public String toString() {
			return "Filho de Menu";
		}
	},

	FILHO_MODULO {
		@Override
		public String toString() {
			return "Filho de Módulo";
		}
	},

}