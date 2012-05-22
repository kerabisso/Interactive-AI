//The height map of the water surface
uniform sampler2D heightMap;
//Detrmines if the current frame is the init frame
uniform bool init;

varying vec2 texCoord;

//Has to render to heightMap
void main (void)
{
	//Returns a "zero" water surface on init. Otherwise it does nothing.
	if(init) gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
	else gl_FragColor = texture2D(heightMap, texCoord);
}
