<?php
	//error_reporting(0);
	//$cfg['Servers'][$i]['AllowRoot'] = true;
	header('Content-Type: text/html; charset=utf-8');
	header('Content-Type: application/json');
	$link = new mysqli('localhost', 'root', 'admin', 'fridge');
	mysqli_query($link, "SET NAMES utf8");
	//   Поиск
	if($_GET['query'] == 'search')
	{
		$ans = array();
		$query = "SELECT id, name FROM recipes WHERE name LIKE '%".mysqli_real_escape_string($link, $_GET['str'])."%' ORDER BY  `recipes`.`popularity` DESC LIMIT 0, ".mysqli_real_escape_string($link, $_GET['count']);
		$res = mysqli_query($link, $query);
		for($i = 0; $i < $res->num_rows; $i++)
		{
			$res->data_seek($i);
			$row = $res->fetch_assoc();
			$recipe = array("name"=>$row['name'], "id"=>$row['id']);
			if(isset($_GET['test']))
			{
				echo $row['name']."\n";
			}
			$ans[$i] = $recipe;
		}
		echo stripcslashes(json_encode($ans, JSON_UNESCAPED_UNICODE));
	}
	// Получение еды по id
	if($_GET['query'] == 'get_food')
	{
		$query = "SELECT * FROM food WHERE id = ".mysqli_real_escape_string($link, $_GET['id']);
		$res = mysqli_query($link, $query);
		$res->data_seek(0);
		$food = $res->fetch_assoc();
		echo json_encode($food, JSON_UNESCAPED_UNICODE);
	}
	// Получение категории по id
	if($_GET['query'] == 'get_category_name')
	{
		$query = "SELECT * FROM category WHERE id = ".mysqli_real_escape_string($link, $_GET['id']);
		$res = mysqli_query($link, $query);
		$res->data_seek(0);
		$category = $res->fetch_assoc();
		echo json_encode($category, JSON_UNESCAPED_UNICODE);
	}
	// Получение рецепта по id
	if($_GET['query'] == 'get_recipe')
	{
		$query = "SELECT * FROM recipes WHERE id = ".mysqli_real_escape_string($link, $_GET['id']);
		$res = mysqli_query($link, $query);
		$res->data_seek(0);
		$recipe = $res->fetch_assoc();
		echo json_encode($recipe, JSON_UNESCAPED_UNICODE);
	}
	//   Поиск по ингредиентам
	if($_GET['query'] == 'calc')
	{
		$time = time();
		$json = array();
		$arr = explode('@', $_GET['arr']);
		$count = array();
		for($i = 0; $i < count($arr); $i++)
		{
			list($a, $b) = explode('|', $arr[$i]);
			$count[$a] = $b;
		}
		$has = array();
		$query = "SELECT calc_recipe.recipe_id, calc_recipe.food_id, calc_recipe.count FROM calc_recipe INNER JOIN recipes ON calc_recipe.recipe_id = recipes.id WHERE name LIKE '%".$_GET['str']."%'";
		$all = array();
		$res = mysqli_query($link, $query);
		for($i = 0; $i < $res->num_rows; $i++)
		{
			$res->data_seek($i);
			$cur = $res->fetch_assoc();
			if($count[$cur['food_id']] < $cur['count'])
			{
				$all[$cur['recipe_id']]++;
			}
			$has[$cur['recipe_id']] = 1;
		}
		$c = 0;
		for($i = 14000; $i <= 60000; $i++)
		{
			if($has[$i] && $all[$i] <= $_GET['max'])
			{
				$json[$c]['id'] = $i;
				$query = "SELECT name FROM recipes WHERE id = ".$i;
				$res = mysqli_query($link, $query);
				$res->data_seek(0);
				$json[$c]['name'] = $res->fetch_assoc()['name'];
				$c++;
			}
		}
		echo json_encode($json, JSON_UNESCAPED_UNICODE);
	}
	// Получение всех ингредиентов
	if($_GET['query'] == 'get_all_food')
	{
		$query = "SELECT * FROM food";
		$res = mysqli_query($link, $query);
		$json = array();
		for($i = 0; $i < $res->num_rows; $i++)
		{
			$res->data_seek($i);
			$food = $res->fetch_assoc();
			$json[$i] = $food;
		}
		echo json_encode($json, JSON_UNESCAPED_UNICODE);
	}
	// Найти id еды по названию
	if($_GET['query'] == 'food_search')
	{
		$query = "SELECT id, name FROM food WHERE name LIKE '%".$_GET['str']."%';";
		$res = mysqli_query($link, $query);
		for($i = 0; $i < $res->num_rows; $i++)
		{
			$res->data_seek($i);
			$name = $res->fetch_assoc();
			echo $name['name']."  ".$name['id']."\n";
		}
	}
	//   Тест
	if($_GET['query'] == 'test')
	{
	}
?>
