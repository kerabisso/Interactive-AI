uniform sampler2D jvr_Texture0;
varying vec2 texCoord;

uniform float threshhold;
uniform float factor;

void main (void)
{
	vec4 color = texture2D(jvr_Texture0, texCoord);
	
	color -= threshhold;
	color *= factor;
	
	gl_FragColor = color;
}
