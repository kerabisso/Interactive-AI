//The height and velocity map of the water surface
uniform sampler2D velMap, heightMap;
//Detrmines if the current frame is the init frame
uniform bool init;
//The time needed for the pervious frame in sec
uniform float deltaT;
//A multiplier for deltaT
uniform float simulationSpeed;
//The size of a single interaction (radius) in texture coordinate space
uniform float interactionSize;
//The number of vec2s that are used in the interactions array 
uniform int interactions_size;
//Interaction points
uniform vec2 interactions[256];
//The aspect ratio of the openGL frame
uniform vec2 ratio;

varying vec2 texCoord;

//Values [-1.0, 1.0] are stored in a color texture.
//If the value > 0 it is stored in the red channel.
//If the value is < 0 it is stored in the greed channel.
//The other two channels are unused.
//However the alpha channel is set to 1.0 for displaying.
//The following two methods convert values from/to a smapler2D
//while dealing wiht interpolation issues.

//Converts color to a [-1.0, 1.0] value.
float fromColor(vec4 value) 
{
	if(value.r > value.g) return value.r;
	else return -value.g;
}

//Converts a [-1.0, 1.0] value to a color.
vec4 toColor(float value)  
{
	if(value > 0.0) return vec4(value,0.0,0.0,1.0);
	else return vec4(0.0,-value,0.0,1.0);
}

//Checks if the current fragment overlaps with an interaction area
bool checkForInteraction(float maxDistcance) {
	//Equalize coordinates (to get a real circle on screen)
	vec2 normalizedTexCoords = texCoord * ratio;
	//check all interactions
	for(int i = 0; i < interactions_size; ++i) {
		float d = distance(interactions[i] * ratio, normalizedTexCoords);
		if(d < maxDistcance) return true; 
	}
	return false;
}

//Simulates a water surface a a set of springs (Part 2/2 - linear velocity application)
//Has to render to heightMap
void main (void)
{	
	//The internal delta is used to pervent simulation instability
	//caused from big deltas and stiff springs.
	float internalDeltaT = deltaT * 100.0 * simulationSpeed;
	if(internalDeltaT > 0.25) internalDeltaT = 0.25;

	//Init with a height of zero
	if(init) gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
	else {
		//Fragments overlapping an interaction area are maximally traveled
		if(checkForInteraction(interactionSize)) gl_FragColor = vec4(0.0, 1.0, 0.0, 1.0);
		else {
			//Get height
			float h = fromColor(texture2D(heightMap, texCoord));
			//Apply linear velocity
			h += fromColor(texture2D(velMap, texCoord)) * internalDeltaT;
			gl_FragColor = toColor(h);
		}
	}

}
