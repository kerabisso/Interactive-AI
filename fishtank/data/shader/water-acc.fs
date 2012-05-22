//The height and velocity map of the water surface
uniform sampler2D velMap, heightMap;
//Detrmines if the current frame is the init frame
uniform bool init;
//The time needed for the pervious frame in sec
uniform float deltaT;
//A multiplier for deltaT
uniform float simulationSpeed;
//Weights for the near and far interaction strength
uniform float near, far;
//The distance of the horizontal and vertical neighbors in texture coordinates.
uniform float dx, dy;

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

//Simulates a water surface a a set of springs (Part 1/2 - acceleration application)
//Has to render to velMap
void main (void)
{	
	//The internal delta is used to pervent simulation instability
	//caused from big deltas and stiff springs.
	float internalDeltaT = deltaT * 100.0 * simulationSpeed;
	if(internalDeltaT > 0.25) internalDeltaT = 0.25;

	//The texture coordinates of the Moore neighbors
	vec2 ul = texCoord + vec2(-dx,-dy);
	vec2 u = texCoord + vec2(0.0,-dy);
	vec2 ur = texCoord + vec2(dx,-dy);
	vec2 l = texCoord + vec2(-dx,0.0);
	vec2 r = texCoord + vec2(dx,0.0);
	vec2 ll = texCoord + vec2(-dx,dy);
	vec2 lower = texCoord + vec2(0.0,dy);
	vec2 lr = texCoord + vec2(dx,dy);

	//Init with no velocity
	if(init) gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
	else {
		
		//Get height and linear velocity
		float h = fromColor(texture2D(heightMap, texCoord));
		float vel = fromColor(texture2D(velMap, texCoord));

		//Apply spring law
		vel += -h * internalDeltaT;

		//Determine factor for near and far neighbors
		float farFactor = far * internalDeltaT;
		float nearFactor = near * internalDeltaT;

		//Apply acceleration based on the neighbors and their distance
		vel -= (h - fromColor(texture2D(heightMap, ul)))    * farFactor;
		vel -= (h - fromColor(texture2D(heightMap, ur)))    * farFactor;
		vel -= (h - fromColor(texture2D(heightMap, ll)))    * farFactor;
		vel -= (h - fromColor(texture2D(heightMap, lr)))    * farFactor;
		vel -= (h - fromColor(texture2D(heightMap, u)))     * nearFactor;
		vel -= (h - fromColor(texture2D(heightMap, l)))     * nearFactor;
		vel -= (h - fromColor(texture2D(heightMap, r)))     * nearFactor;
		vel -= (h - fromColor(texture2D(heightMap, lower))) * nearFactor;

		//Apply damping
		vel *= 0.99;	

		gl_FragColor = toColor(vel);
	}	
}
