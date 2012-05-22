uniform sampler2D jvr_Texture0;
uniform sampler2D jvr_Texture1;
varying vec2 texCoord;

vec4 blur(float factor)
{   	
   	int iteration = 6;
   	
   	vec4 final_color = vec4(0,0,0,1);
   	
   	for(int x=-iteration/2; x<iteration/2+1; x++)
   	{
   		for(int y=-iteration/2; y<iteration/2+1; y++)
   		{
   			vec2 offset = factor * vec2(float(x)/512.0, float(y)/384.0);
   			vec2 texC = texCoord+offset;
   			if(texC.x>1.0 || texC.x<0.0 || texC.y>1.0 || texC.y<0.0)
   			{
   				texC = texCoord;
   			}
   			final_color += texture2D(jvr_Texture0, texC);
   		}
   	}

	final_color /= float(iteration + 1) * float(iteration + 1); 
   	
   	final_color.w = 1.0;
	return final_color;
}

void main (void)
{
	gl_FragColor = blur(2.5) + texture2D(jvr_Texture1, texCoord);
}