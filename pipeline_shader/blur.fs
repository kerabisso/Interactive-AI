uniform sampler2D jvr_Texture0;
uniform int iterations;
varying vec2 texCoord;

void main (void)
{
   	vec4 final_color = vec4(0,0,0,1);
   	
   	for(int x=-iterations/2; x<iterations/2+1; x++)
   	{
   		for(int y=-iterations/2; y<iterations/2+1; y++)
   		{
   			vec2 texC = texCoord + vec2(float(x)/200.0, float(y)/200.0);
   			final_color += texture2D(jvr_Texture0, texC);
   		}
   	}
   	
   	final_color /= (iterations+1)*(iterations+1);
   	
   	final_color.w = 1.0;
	gl_FragColor = final_color;
}
