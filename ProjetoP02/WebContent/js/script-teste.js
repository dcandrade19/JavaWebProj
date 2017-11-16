var alerta = document.getElementById('alerta');
var modo = "none"
function alertar(opc) {
	if(opc == 1) {
		
		
		modo = "flex";
		alerta.setAttribute("style","display:"+modo);
	}
}