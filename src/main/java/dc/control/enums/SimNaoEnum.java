package dc.control.enums;

public enum SimNaoEnum {

	S {
		@Override
		public String toString() {
			return "SIM";
		}
	},

	N {
		@Override
		public String toString() {
			return "NÃO";
		}
	},

}