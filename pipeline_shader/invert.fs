uniform sampler2D jvr_Texture0;
varying vec2 texCoord;

void main (void)
{
   	vec4 final_color = texture2D(jvr_Texture0, texCoord);
   	
   	final_color.x = 1-final_color.x;
   	final_color.y = 1-final_color.y;
   	final_color.z = 1-final_color.z;
   	final_color.w = 1.0;
   	
	gl_FragColor = final_color;
}
