uniform sampler2D jvr_Texture0;
varying vec2 texCoord;

float linearizeDepth()
{
  float n = 0.1;
  float f = 100.0;
  float z = texture2D(jvr_Texture0, texCoord).x;
  return (2.0 * n) / (f + n - z * (f - n));
}

void main (void)
{
	float z = linearizeDepth();
	vec4 final_color = vec4(z,z,z,1);
	
	gl_FragColor = final_color;
}
