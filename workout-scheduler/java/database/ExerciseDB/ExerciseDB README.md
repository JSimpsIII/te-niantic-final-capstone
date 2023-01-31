https://rapidapi.com/justin-WFnsXH_t6/api/exercisedb/details

ExerciseDB
by Justin Mozley

README
A database API containing 1300+ exercises with body part, target muscle equipment necessary, and a form and follow-through animation.

For questions, comments, concerns, and/or feature requests please do not hesitate to reach out to support@devworxconsulting.com

Below are lists of query strings to use for the labelled endpoints
/exercises/equipment/{type}

  "assisted",
  "band",
  "barbell",
  "body weight",
  "bosu ball",
  "cable",
  "dumbbell",
  "elliptical machine",
  "ez barbell",
  "hammer",
  "kettlebell",
  "leverage machine",
  "medicine ball",
  "olympic barbell",
  "resistance band",
  "roller",
  "rope",
  "skierg machine",
  "sled machine",
  "smith machine",
  "stability ball",
  "stationary bike",
  "stepmill machine",
  "tire",
  "trap bar",
  "upper body ergometer",
  "weighted",
  "wheel roller"
/exercises/target/{target}

  "abductors",
  "abs",
  "adductors",
  "biceps",
  "calves",
  "cardiovascular system",
  "delts",
  "forearms",
  "glutes",
  "hamstrings",
  "lats",
  "levator scapulae",
  "pectorals",
  "quads",
  "serratus anterior",
  "spine",
  "traps",
  "triceps",
  "upper back"
/exercises/bodyPart/{bodyPart}

  "back",
  "cardio",
  "chest",
  "lower arms",
  "lower legs",
  "neck",
  "shoulders",
  "upper arms",
  "upper legs",
  "waist"
Please use %20 in place of whitespace/spacing between words in a query string
Ex. /exercises/bodyPart/upper%20legs